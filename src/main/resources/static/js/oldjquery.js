			function showGrid() {
				$.get("http://localhost:8080/ajaxproducts", function(data) {
					$.each(data, function(i, product) {
						$("#products").append(
					
		"<li class='clearfix'><section class='left'>"+
				    "<img alt='default thumb' class='thumb' src='images/products/grid-default-thumb.png'>"+
 			        "<h3 id='product_name' class='alwaysOnTop'>"+product.name+"</h3>"+
     			    "<span class='meta alwaysOnTop'>"+product.product_id+"</span>"+
			        "</img>"+
				   "</section>"+

				  "<section class='right'>"+
					"<a class='price'>"+
			    "<span class='alwaysOnTopPrice'>"+product.price+"€"+"</span>"+
			    "</a>"+
			    "<span class='darkview gridviewCl'>"+
 		      	  "<a class='editbtngridview' href='javascript:void(0);'>"+
        	   			"<img type='button' data-toggle='modal' class='grow' alt='Delete' src='images/read-delete-btn.png'/>"+
         	    		"<img type='button' data-toggle='modal' data-target='#myModal' class ='grow darkenimg' alt='Edit' src='images/add-to-edit-btn.png'/>"+
     		 	   "</a>"+
     		 	   "<a class='gridColumn'>"+
        	   			 "<span class='description'>"+product.description+"</span>"+
     			    "</a>"+
   			    "</span>"+
			 "</section>"+
		"</li>"		
						);								
					});
			});
		};
		
				  function showlist() {
					$.get("http://localhost:8080/ajaxproducts", function(data) {
					
						$.each(data, function(i, product) {
							$("#products").append(
									
					"<li id="+product.id+" class='clearfix'>"+
						"<section class='left'>"+
							"<img src='images/products/list-default-thumb.png' alt='default thumb' class='thumb'>"+
							"<h3 >"+product.name+"</h3>"+
							"<span class='meta'>"+product.product_id+"</span>"+
							"<br class='description'><span class='description'>"+product.description+"</span>"+
						"</section>"+
						
						"<section class='right'>"+
							"<span class='darkview'>"+
							"<a data-toggle='modal' data-target='#myModal' class='editBtn'>"+
							"<img class ='grow' src='images/add-to-edit-btn.png' alt='Edit'></a>"+
							"<a  class='xBtn'><img class='grow' src='images/read-delete-btn.png' alt='Delete'></a>"+
							"<span class='price'>"+product.price+"€</span>"+
							"</span>"+
						"</section>"+
					"</li>"
							);								
						});
				});
				  };
					
		$(document).ready(function() {
			  $( "#products" ).empty();
			  showlist();
			  $( "#products" ).removeClass( "grid" );
			  $( "#products" ).addClass( "list" );
			
			  $('#listview').click(function clicklist(){
				  $( "#products" ).empty();
				  showlist();
				  $( "#products" ).removeClass( "grid" );
				  $( "#products" ).addClass( "list" );
			 		 });
			  $('#gridview').click(function clickgrid(){
				  $( "#products" ).empty();
				  showGrid();
				  $( "#products" ).removeClass( "list" );
				  $( "#products" ).addClass( "grid" );
		 		 });
			  $('.editBtn').on('click', function() {
			        var val = $(this).closest('li').attr('id');
			        $('#product-name').text(val);
				  });
		});
		
		 $("#savebtn").click(function () {
		        $.ajax({
		            type: "POST",
		            url: "http://localhost:8080/saveproduct",
		            data: "{name: '" + $("#txtName").val() + "'}",
		            contentType: "application/json; charset=utf-8",
		            dataType: "json",
		            success: function (r) {
		                $("#dialog").html(r.d);
		                $("#dialog").dialog("open");
		            }
		        });
		    });
		 