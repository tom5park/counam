(function() {
	var slice = Array.prototype.slice;
	
	var update = function(array, args) {
		var arrayLength = array.length, length = args.length;
		while (length--) array[arrayLength + length] = args[length];
		return array;
	};
	
	var merge = function(array, args) {
		array = slice.call(array, 0);
		return update(array, args);
	};
	
	var isUndefined = function(object) {
		return typeof object === "undefined";
	};

	Function.prototype.bind = function (context) {
		if (arguments.length < 2 && isUndefined(arguments[0])) return this;
		var __method = this, args = slice.call(arguments, 1);
		return function() {
			var a = merge(args, arguments);
			return __method.apply(context, a);
		};
	};

	Function.prototype.bindAsEventListener = function (context) {
		var __method = this, args = slice.call(arguments, 1);
		return function(event) {
			var a = update([event || window.event], args);
			return __method.apply(context, a);
		};
	};

	Function.prototype.curry = function() {
		if (!arguments.length) return this;
		var __method = this, args = slice.call(arguments, 0);
		return function() {
			var a = merge(args, arguments);
			return __method.apply(this, a);
		};
	};
	
	Array.prototype.each =  function(iterator) {
	    for (var i = 0, length = this.length; i < length; i++)
	      iterator(this[i]);
	};
	
	String.prototype.endsWith = function(suffix) {
	    return this.indexOf(suffix, this.length - suffix.length) !== -1;
	};
	
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g,'');
	};
})();


(function() {
	$.views.helpers({
		format: function(val, format) {
			return val.toUpperCase();
		}
	});
	$.views.converters({
		yyyyMMdd: function(d) {
			return d.substr(0, 10);
		}
	});
})();