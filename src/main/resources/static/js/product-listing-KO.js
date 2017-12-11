
formatMoney = function(value){
	if(value){
		return value + " €";
	}
	
	return "- €";
};

var Item = function(item){
	var self = this;
	$.extend(self, item);
	
	self.name = ko.observable(item.name);
	
	self.price = ko.observable(item.price);
	self.priceText = ko.observable(formatMoney(item.price));
	
	self.price.subscribe(function(newValue){
		self.priceText(formatMoney(newValue));
	});
};


var AppViewModel = function() {
    var self = this;
 
    self.products = ko.observableArray([]);
    self.updateableItem = ko.observable({});
    self.selectedItem = ko.observable({});
    
    self.listView = ko.observable(true);
    
    self.changeListView = function(){
    	self.listView(true);
    };
    
    self.changeGridView = function(){
    	self.listView(false);
    };
    
    self.init = function(){
    	
    	$.get("./ajaxproducts", function(data) {
    		console.log(data);
    		
    		data.forEach(function(item){
    			self.products.push(new Item(item));
    		});
    		
    		console.log(self.products());
    	});
    };
    
    self.editItem = function(item){
    	console.log(item);
    	self.selectedItem = item;
    	self.updateableItem(new Item(ko.toJS(item)));
    };
    
    self.saveItem = function(){
    	
    	var postData = ko.toJSON(self.updateableItem);
    	console.log(postData);
    
    	 $.ajax({
            type: "POST",
            url: "./saveproduct",
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
        		console.log(data);
    			
    			self.selectedItem.name(self.updateableItem().name());
    			//todo ostatne atributy nasetovat
    			
        		self.updateableItem({});
        		
        		$("#myModal").hide();
            }
        });
    	
    };
    
    self.init();
};
 
var debug = new AppViewModel();
ko.applyBindings(debug);