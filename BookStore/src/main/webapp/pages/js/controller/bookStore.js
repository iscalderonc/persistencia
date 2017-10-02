(function () {
  'use strict';

  angular.module('bookStoreApp',[])
    .controller('bookStoreCtrl', function ($scope, bookService) {
    	
    	// Constant
    	var operationFail = "FAIL";
    	var operationSucessful= "SUCCESSFUL";
    	var operationNotification= "NOTIFICATION";
    	
    	var alertClassSucces = 'alert alert-success fade in';
//    	var messageSucces = 'Success!';
    	var alertClassDanger = 'alert alert-danger fade in';
//    	var messageDanger = 'Error!';
    	var alertClassInfo = 'alert alert-info in';
    	var messageInfo= 'NOTIFICATION';
    	
    	$scope.init = function(){
    		$scope.titleWelcome = "JOSM 2";
    		$scope.titlePresentation = "Unit Test";
        	$scope.book = {};
        	$scope.getAll();
        	$scope.getCategory();
        	$scope.getAllItem();
//        	$scope.getGift();
        	$scope.getNumberBookByCategory();
        	$scope.buttonSubmit = 'Add';
        	$scope.buttonClass = 'btn btn-success';
    	};
    	
    	$scope.submit = function (){
    		$scope.book.category = $scope.book.category.description;
    		if( $scope.book.id == null){
	    		bookService.add($scope.book).then(
	    				function (response) {

	    					var message = '[' + $scope.book.name + '] ';
	    					message += response.description;
	    					$scope.messageStatus = response.status;
	    					
	    					if(response.status == operationSucessful){
	    						$scope.alertClass = alertClassSucces;
	    						$scope.init();
//	    						$scope.getNumberBookByCategory();
	    					}else if(response.status == operationFail){
	    						$scope.alertClass = alertClassDanger;
	    						$scope.getCategoryByLabel($scope.book.category);
	    					}
	    					
	    					$scope.message = message;
	    	            },
	    	            function(errResponse){
	            			 console.error('Error while Add Book.');
	    				}
	    		);
    		}else{
    			bookService.update($scope.book).then(
    					function (response) {
    						var message = '[' + $scope.book.name + '] ';
	    					if(response.status == operationSucessful){
	    						message += response.description;	
	    						$scope.messageStatus = response.status;
	    						$scope.alertClass = alertClassInfo;
	    						$scope.init();
	    					}
	    					$scope.message = message;
    					},
	    	            function(errResponse){
    						console.error('Error while Update Book.');
    					}
    			);
    		}
        };
        
        $scope.getAll = function (){
        	bookService.getAll().then(
					function (response) {
						$scope.books =  response;			
					},
					function(errResponse){
            			 console.error('Error while get All Books.');
    				}	
			);
        };
        
    	$scope.edit = function(id){
    		var data = 'id=' + id;
    		bookService.getBook(data).then(
    				function (response) {
    					if(response != null){
    						$scope.book.id = response.id;
    						$scope.book.name = response.name;
        					$scope.book.author = response.author;
//        					$scope.book.category = response.book.category;
        					$scope.getCategoryByLabel(response.category);
        					$scope.book.page = response.page;
        					$scope.book.price = response.price;
        					$scope.buttonSubmit = 'Update';
        					$scope.buttonClass = 'btn btn-info';
        					$scope.message = '[' + $scope.book.name + '] Update the information the Book.';
        					$scope.messageStatus = messageInfo;
    						$scope.alertClass = alertClassInfo;
        					$scope.getAll();
    					}
    				},
            		function(errResponse){
            			 console.error('Error while getBook(' + id + ')');
            		}
            );
    	};
    	
    	$scope.deleteB = function(id){
    		var data = 'id=' + id;
    		bookService.deleteB(data).then(
    				function (response) {
    					$scope.message = response.description;
						$scope.messageStatus = response.status;
						
    					if(response.status == operationSucessful){
    						$scope.alertClass = alertClassSucces;
    						$scope.getAll();
    					}
    				},
            		function(errResponse){
            			 console.error('Error while deleteBook(' + id + ')');
            		}
            );
    	};
    	
    	$scope.getCategory = function (){
         	bookService.getCategory().then(
 					function (response) {
 						$scope.categorys =  response;
 					},
 					function(errResponse){
             			 console.error('Error while get All Books.');
     				}
 			);
        };
        
        $scope.getCategoryByLabel = function (label){
        	var data = 'label=' + label;
         	bookService.getCategoryByLabel(data).then(
 					function (response) {
 						$scope.book.category = response;
 					},
 					function(errResponse){
             			 console.error('Error while getCategoryByLabel.');
     				}
 			);
        };
        
        $scope.addItem = function(id){
        	var data = 'id=' + id;
    		bookService.addItem(data).then(
    				function (response) {
    					$scope.messageStatus = response.status;
						$scope.message = response.description;    					
    					if(response.status == operationSucessful){
    						$scope.alertClass = alertClassSucces;
    						$scope.getAllItem();
//    						$scope.getGift(1);
    					}
    				},
            		function(errResponse){
            			 console.error('Error while addItem(' + id + ')');
            		}
            );
        };
        
        $scope.getAllItem= function(){
        	bookService.getAllItem().then(
        			function (response) {
        				$scope.items = response;
        				$scope.getAmountTotal();
        			},
        			function(errResponse){
           			 console.error('Error while getAllItem()');
        			}	
        	);
        };
        
        $scope.getGift= function(opc){
        	bookService.getGift().then(
        			function (response) {
						if(opc===1){
							$scope.messageStatus = response.status;
							$scope.alertClass = alertClassSucces;
							$scope.message = response.description;
	    					$scope.totalPay = response.amount;
						}else{
							if(response.amount != undefined && response.amount > 0){
								$scope.alertClass = alertClassInfo;
								$scope.messageStatus = messageInfo;
								$scope.message = response.description;
		    					$scope.totalPay = response.amount;
							}
						}
        			},
        			function(errResponse){
           			 console.error('Error while getGift');
        			}	
        	);
        };
        
        $scope.getNumberBookByCategory= function(){
        	bookService.getNumberBookByCategory().then(
        			function (response) {
        				$scope.bookCategorys = response;
        			},
        			function(errResponse){
           			 console.error('Error while getBookCategoryTotal');
        			}	
        	);
        };
        
        $scope.getAmountTotal= function(){
        	bookService.getAmountTotal().then(
        			function (response) {
	    				$scope.totalPay = response;
	    				$scope.getGift();
        			},
        			function(errResponse){
           			 console.error('Error while getAmountTotal');
        			}	
        	);
        };
    	
    });
}());

