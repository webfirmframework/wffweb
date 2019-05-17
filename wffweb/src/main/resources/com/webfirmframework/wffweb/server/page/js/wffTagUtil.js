
var wffTagUtil = new function() {

	var encoder = wffGlobal.encoder;
	var decoder = wffGlobal.decoder;

	var getStringFromBytes = function(utf8Bytes) {
		return decoder.decode(new Uint8Array(utf8Bytes));
	};
	
	var getTagNameFromCompressedBytes = function(utf8Bytes) {
		
		//# or @ represented for NoTag
		if (utf8Bytes.length == 1) {
			return decoder.decode(new Uint8Array(utf8Bytes));
		}
		
		var lengOfOptmzdBytsOfTgNam = utf8Bytes[0];
		
		if (lengOfOptmzdBytsOfTgNam > 0) {
			
			var tgNamNdxOptmzdByts = [];
			wffBMUtil.concatArrayValuesFromPosition(tgNamNdxOptmzdByts, utf8Bytes, 1, lengOfOptmzdBytsOfTgNam);
			var tgNamNdx = wffBMUtil.getIntFromOptimizedBytes(tgNamNdxOptmzdByts);
			return wffGlobal.NDXD_TGS[tgNamNdx];
		} else {
			
			var tagNameBytes = [];
			var reqBytsLngth = utf8Bytes.length - 1;
			wffBMUtil.concatArrayValuesFromPosition(tagNameBytes, utf8Bytes, 1, reqBytsLngth);
			
			return getStringFromBytes(tagNameBytes);
		}	
		
	};
	var getAttrNameValueFromCompressedBytes = function(utf8Bytes) {
		var lengOfOptmzdBytsOfAttrNam = utf8Bytes[0];
		
		if(lengOfOptmzdBytsOfAttrNam > 0) {
			var attrNamNdxOptmzdByts = [];
			wffBMUtil.concatArrayValuesFromPosition(attrNamNdxOptmzdByts, utf8Bytes, 1, lengOfOptmzdBytsOfAttrNam);
			
			var attrNamNdx = wffBMUtil.getIntFromOptimizedBytes(attrNamNdxOptmzdByts);
			
			var attrValByts = [];
			var attrValLen = utf8Bytes.length - (lengOfOptmzdBytsOfAttrNam + 1);
			wffBMUtil.concatArrayValuesFromPosition(attrValByts, utf8Bytes, lengOfOptmzdBytsOfAttrNam + 1, attrValLen);
			var attrNamVal = [wffGlobal.NDXD_ATRBS[attrNamNdx], getStringFromBytes(attrValByts)];
			return attrNamVal;
		} else {
			var attrNamByts = [];
			var reqBytsLngth = utf8Bytes.length - 1;
			wffBMUtil.concatArrayValuesFromPosition(attrNamByts, utf8Bytes, 1, reqBytsLngth);
			return splitAttrNameValue(getStringFromBytes(attrNamByts));
		}
	};
	
	var appendHtmlAsChildren = function(tag, htmlString) {
		var tmpDv = document.createElement('div');
		tmpDv.innerHTML = htmlString;

		for (var i = 0; i < tmpDv.childNodes.length; i++) {
			var cn = tmpDv.childNodes[i];
			
			if (cn.nodeName === '#text') {
				tag.appendChild(document.createTextNode(cn.nodeValue));
			} else {
				// must clone otherwise will not be appended
				tag.appendChild(cn.cloneNode(true));
			}
		}
	};

	//Not required for now
//	this.createTagFromBytes = function(bytes) {
//		var htmlString = getStringFromBytes(bytes);
//		var div = document.createElement('div');
//		div.innerHTML = htmlString;
//		return div.firstChild;
//	};

	this.getTagByTagNameAndWffId = function(tagName, wffId) {
		console.log('getTagByTagNameAndWffId tagName', tagName, 'wffId', wffId);
		var elements = document.querySelectorAll(tagName + '[data-wff-id="'
				+ wffId + '"]');
		if (elements.length > 1) {
			console.log('getTagByTagNameAndWffId multiple tags with same wff id');
			wffLog('multiple tags with same wff id', 'tagName', 'wffId', wffId);
		}
		return elements[0];
	};

	this.getTagByWffId = function(wffId) {
		console.log('getTagByTagNameAndWffId ', 'wffId', wffId);
		var elements = document.querySelectorAll('[data-wff-id="' + wffId
				+ '"]');
		if (elements.length > 1) {
			wffLog('multiple tags with same wff id', 'wffId', wffId);
		}
		return elements[0];
	};

	/*
	 * this method will return string wff id from wff id bytes
	 */
	this.getWffIdFromWffIdBytes = function(wffIdBytes) {

		var sOrC = decoder.decode(new Uint8Array([ wffIdBytes[0] ]));
		var intBytes = [];
		for (var i = 1; i < wffIdBytes.length; i++) {
			intBytes.push(wffIdBytes[i]);
		}
		var intId = wffBMUtil.getIntFromOptimizedBytes(intBytes);
		return sOrC + intId;

	};

	this.getWffIdBytesFromTag = function(tag) {
		// the first char will be either C or S where C stands for Client and S
		// stands for Server
		console.log('tag', tag);
		var attrValue = tag.getAttribute("data-wff-id");
		console.log('attrValue', attrValue);

		if (attrValue == null) {
			attrValue = "C" + wffGlobal.getUniqueWffIntId();
			tag.setAttribute("data-wff-id", attrValue);
		}

		var sOrC = attrValue.substring(0, 1);

		var sOrCUtf8Bytes = encoder.encode(sOrC);

		var wffIdBytes = [ sOrCUtf8Bytes[0] ];

		console.log('sOrCUtf8Bytes', sOrCUtf8Bytes[0]);

		var intId = attrValue.substring(1, attrValue.length);

		var intIdBytes = wffBMUtil.getOptimizedBytesFromInt(parseInt(intId));

		wffIdBytes = wffIdBytes.concat(intIdBytes);

		console.log('wffIdBytes', wffIdBytes);
		return wffIdBytes;
	};

	var splitAttrNameValue = function(attrNameValue) {
		// attrNameValue will contain string like name=attr-name
		var attrName;
		var attrValue;
		var indexOfSeparator = attrNameValue.indexOf('=');
		if (indexOfSeparator != -1) {
			attrName = attrNameValue.substring(0, indexOfSeparator);
			attrValue = attrNameValue.substring(indexOfSeparator + 1,
					attrNameValue.length);
		} else {
			attrName = attrNameValue;
			attrValue = '';
		}
		return [ attrName, attrValue ];
	};

	this.splitAttrNameValue = splitAttrNameValue;

	// getWffIdBytesFromTag = this.getWffIdBytesFromTag;
	// var div = document.createElement('div');
	// div.setAttribute('data-wff-id', 'S255');
	// console.log('div', div);
	// var v = getWffIdBytesFromTag(div);
	// console.log('v' ,v);

	this.createTagFromWffBMBytes = function(bytes) {
		var nameValues = wffBMUtil.parseWffBinaryMessageBytes(bytes);

		var superParentNameValue = nameValues[0];
		var superParentValues = superParentNameValue.values;

		var allTags = [];

		var parent;
		var parentTagName = getStringFromBytes(superParentValues[0]);
		if (parentTagName === '#') {
			var text = getStringFromBytes(superParentValues[1]);
			parent = document.createDocumentFragment();
			parent.appendChild(document.createTextNode(text));
		} else if (parentTagName === '@') {
			// @ short for html content
			var text = getStringFromBytes(superParentValues[1]);
			parent = document.createDocumentFragment();			
			appendHtmlAsChildren(parent, text);
			
		} else {

			parent = document.createElement(parentTagName);

			for (var i = 1; i < superParentValues.length; i++) {
				var attrNameValue = splitAttrNameValue(getStringFromBytes(superParentValues[i]));
				//value attribute doesn't work with setAttribute method
				//should be called before setAttribute method
				parent[attrNameValue[0]] = attrNameValue[1];
				parent.setAttribute(attrNameValue[0], attrNameValue[1]);
			}
		}

		allTags.push(parent);

		for (var i = 1; i < nameValues.length; i++) {
			var name = nameValues[i].name;
			var values = nameValues[i].values;

			var tagName = getStringFromBytes(values[0]);

			var child;

			if (tagName === '#') {
				var text = getStringFromBytes(values[1]);
				child = document.createDocumentFragment();
				child.appendChild(document.createTextNode(text));
			} else if (tagName === '@') {
				// @ short for html content
				var text = getStringFromBytes(values[1]);
				child = document.createDocumentFragment();				
				appendHtmlAsChildren(child, text);
			} else {
				child = document.createElement(tagName);

				for (var j = 1; j < values.length; j++) {
					var attrNameValue = splitAttrNameValue(getStringFromBytes(values[j]));
					//value attribute doesn't work with setAttribute method
					//should be called before setAttribute method
					child[attrNameValue[0]] = attrNameValue[1];
					child.setAttribute(attrNameValue[0], attrNameValue[1]);
				}
			}

			allTags.push(child);

			var parentIndex = wffBMUtil.getIntFromOptimizedBytes(name);
			allTags[parentIndex].appendChild(child);
		}

		return parent;
	};
	
	this.createTagFromCompressedWffBMBytes = function(bytes) {
		var nameValues = wffBMUtil.parseWffBinaryMessageBytes(bytes);

		var superParentNameValue = nameValues[0];
		var superParentValues = superParentNameValue.values;

		var allTags = [];

		var parent;
		var parentTagName = getTagNameFromCompressedBytes(superParentValues[0]);
		
		if (parentTagName === '#') {
			var text = getStringFromBytes(superParentValues[1]);
			parent = document.createDocumentFragment();
			parent.appendChild(document.createTextNode(text));
		} else if (parentTagName === '@') {
			// @ short for html content
			var text = getStringFromBytes(superParentValues[1]);
			parent = document.createDocumentFragment();			
			appendHtmlAsChildren(parent, text);
			
		} else {

			parent = document.createElement(parentTagName);

			for (var i = 1; i < superParentValues.length; i++) {
				var attrNameValue = getAttrNameValueFromCompressedBytes(superParentValues[i]);
				//value attribute doesn't work with setAttribute method
				//should be called before setAttribute method
				parent[attrNameValue[0]] = attrNameValue[1];
				parent.setAttribute(attrNameValue[0], attrNameValue[1]);
			}
		}

		allTags.push(parent);

		for (var i = 1; i < nameValues.length; i++) {
			var name = nameValues[i].name;
			var values = nameValues[i].values;

			var tagName = getTagNameFromCompressedBytes(values[0]);

			var child;

			if (tagName === '#') {
				var text = getStringFromBytes(values[1]);
				child = document.createDocumentFragment();
				child.appendChild(document.createTextNode(text));
			} else if (tagName === '@') {
				// @ short for html content
				var text = getStringFromBytes(values[1]);
				child = document.createDocumentFragment();				
				appendHtmlAsChildren(child, text);
			} else {
				child = document.createElement(tagName);

				for (var j = 1; j < values.length; j++) {
					var attrNameValue = getAttrNameValueFromCompressedBytes(values[j]);
					//value attribute doesn't work with setAttribute method
					//should be called before setAttribute method
					child[attrNameValue[0]] = attrNameValue[1];
					child.setAttribute(attrNameValue[0], attrNameValue[1]);
				}
			}

			allTags.push(child);

			var parentIndex = wffBMUtil.getIntFromOptimizedBytes(name);
			allTags[parentIndex].appendChild(child);
		}

		return parent;
	};
	
	if(wffGlobal.CPRSD_DATA) {
		this.createTagFromWffBMBytes = this.createTagFromCompressedWffBMBytes;
	}

};