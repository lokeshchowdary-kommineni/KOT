/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  $(window).load(function(){          
   $.widget('custom.mcautocomplete', $.ui.autocomplete, {
    _create: function() {
      this._super();
      this.widget().menu( "option", "items", "> :not(.ui-widget-header)" );
    },
    _renderMenu: function(ul, items) {
        var self = this, thead;
    
        if (this.options.showHeader) {
            table=$('<div class="ui-widget-header" style="width:100%"></div>');
            // Column headers
            $.each(this.options.columns, function(index, item) {
                table.append('<span style="float:left;min-width:' + item.minWidth + ';">' + item.name + '</span>');
            });
			table.append('<div style="clear: both;"></div>');
            ul.append(table);
        }
        // List items
        $.each(items, function(index, item) {
            self._renderItem(ul, item);
        });
    },
    _renderItem: function(ul, item) {
		var t = '',
			result = '';
		
		$.each(this.options.columns, function(index, column) {
			t += '<span style="float:left;min-width:' + column.minWidth + ';">' + item[column.valueField ? column.valueField : index] + '</span>'
		});
	
		result = $('<li></li>')
			.data('ui-autocomplete-item', item)
			.append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>')
			.appendTo(ul);
		return result;
    }
});


       
  
                
 var columns = [{name: 'Name', minWidth: '40%', valueField: 'label'}, {name: 'Code', minWidth:'40%', valueField: 'code'},{name: 'id', minWidth:'20%', valueField: 'id'}];

    
    $("#customerName").mcautocomplete({
  showHeader: true,
  columns: columns,
  source: function(request, response) {
        $.ajax({
            url: "GetCustomer.html",
            dataType: "json",
            type: "GET",
            data: {
                term: request.term
            },
            success: function(data){
                response( $.map( data, function( item ) {
                    //alert(item.label);
                   
                    return {
                        value: item.customerFname,   
                        label: item.customerFname,
                        id: item.customerid,
                        code:item.phone
                         // EDIT
                    }
                }));

             }
        })
    },
  select: function(event, ui) {
      //alert(ui.item.value );
    this.value = (ui.item.value ? ui.item.value : '');
    return false;
  }
});
});