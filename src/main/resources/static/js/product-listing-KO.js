formatMoney = function(value) {
	if (value) {
		return value + " €";
	}

	return "- €";
};

var Item = function(item) {
	var self = this;
	$.extend(self, item);

	self.name = ko.observable(item.name);
	self.image = ko.observable(item.image);
	self.price = ko.observable(item.price);
	self.priceText = ko.observable(formatMoney(item.price));

	self.price.subscribe(function(newValue) {
		self.priceText(formatMoney(newValue));
	});

	self.image = ko.observable(item.image);
	self.fileSelect = function(elemet, event) {
		var files = event.target.files;// FileList object

		// Loop through the FileList and render image files as thumbnails.
		for (var i = 0, f; f = files[i]; i++) {

			// Only process image files.
			if (!f.type.match('image.*')) {
				self.image(undefined);
				console.error("WRONG TYPE");
				continue;
			}

			var reader = new FileReader();

			// Closure to capture the file information.
			reader.onload = (function(theFile) {
				return function(e) {
					self.image(e.target.result);
				};
			})(f);
			// Read in the image file as a data URL.
			reader.readAsDataURL(f);
		}
	};

	self.canSave = ko.computed(function() {

		if (!self.name() || !self.price() || !self.image()) {
			return false;
		}

		if (self.image() === item.image && self.name() === item.name
				&& Number(self.price()) === item.price) {
			return false;
		}

		return true;
	});
};

var AppViewModel = function() {
	var self = this;

	self.products = ko.observableArray([]).extend({
		paged : {
			pageSize : Cookies.get('pageSize') || 15,
			pageGenerator : 'sliding'
		}
	});
	self.products.pageGenerator.windowSize(12);
	self.setPage = function(newPage) {
		self.products.pageNumber(newPage);
	};
	self.pagesNumberElement = ko.observable(false);

	self.updateableItem = ko.observable(undefined);
	self.selectedItem = ko.observable({});

	self.listView = ko.observable(true);

	self.changeListView = function() {
		self.listView(true);
	};

	self.changeGridView = function() {
		self.listView(false);
	};

	self.init = function() {

		$.get("./activeproducts", function(data) {
			console.log(data);

			data.forEach(function(item) {
				self.products.push(new Item(item));
			});

			console.log(self.products());
		});
	};

	self.editItem = function(item) {
		console.log(item);
		self.selectedItem = item;
		self.updateableItem(new Item(ko.toJS(item)));
	};

	self.saveItem = function() {

		var postData = ko.toJSON(self.updateableItem) || {};
		console.log(postData);

		$.ajax({
			type : "POST",
			url : "./saveproduct",
			data : postData,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);

				self.selectedItem.name(self.updateableItem().name());
				// todo ostatne atributy nasetovat

				self.updateableItem(undefined);

				$("#myModal").hide();
			}
		});

	};

	self.deleteItem = function(data) {
		var self = this;
		console.log(data);
		event.stopPropagation();
		if (confirm("Do you want to delete?")) {
			this.click;
			$.ajax({
				type : "POST",
				url : "./hideproduct/" + data,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					console.log(data);

					
					
				}
			});
		} else {
			
		}
		event.preventDefault();

	};

	self.setPageSize = function(pageSize) {
		Cookies.set('pageSize', pageSize);
		window.location.reload(true);
	};

	self.init();
};

var debug = new AppViewModel();
ko.applyBindings(debug);