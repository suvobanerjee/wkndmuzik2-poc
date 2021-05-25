console.log("Validation js called");


(function(window, document, $, Granite, undefined){
	"use strict";
	
	$(document).on("click",".cq-dialog-submit", function(e){
		e.stopPropagation();
		e.preventDefault();
		
		var $form = $(this).closest("form.foundation-form");
		var textInput = document.getElementsByName("./text")[0].value,
			sizeInput = document.getElementsByName("./length")[0].value
		
		
		console.log(textInput);
		console.log(sizeInput);

		if(textInput.length == 0)
			{
				Granite.author.ui.helpers.prompt({
					title: Granite.I18n.get("Invalid Input"),
					message: "Please enter something!!!",
					actions:[{
						id: "Cancel",
						text: "Cancel",
						className: "coral-button"
					}],
					callback: function(actionId){
						if(actionId === "Cancel"){
							
						}
					}
				});
			}
		else if(sizeInput == 0 || isNaN(sizeInput))
			{

				Granite.author.ui.helpers.prompt({
				title: Granite.I18n.get("Invalid Input"),
				message: "Please enter a number!!!",
				actions:[{
					id: "ok",
					text: "OK",
					className: "coral-button"
				}],
				callback: function(actionId){
					if(actionId === "ok"){
						
					}
				}
			});
			}
		else
			{
				$form.submit();
			}
		
	});
})(window, document, Granite.$, Granite);

