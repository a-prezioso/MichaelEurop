$('.mydatatable').DataTable({
	scrollY: 283,
	scrollX: true,
	scrollCollapse: true,
	paging: false,
	"language": {
	    "search": "Cerca:",
		"zeroRecords": "Nessun risultato da mostrare",
		"info":           "Mostrati _TOTAL_ risultati",
		"infoEmpty":      "Mostrati 0 risultati",
		"infoFiltered":   "(filtrati su un totale di _MAX_ risultati)"
	  }
});

$('.mydatatablereport').DataTable({
	scrollY: 283,
	scrollX: true,
	scrollCollapse: true,
	paging: false,
	"language": {
	    "search": "Cerca:",
		"zeroRecords": "Nessun risultato da mostrare",
		"info":           "Mostrati _TOTAL_ risultati",
		"infoEmpty":      "Mostrati 0 risultati",
		"infoFiltered":   "(filtrati su un totale di _MAX_ risultati)"
	  },
	"createdRow": function( row, data, dataIndex ) {
		var valore = data[4];
		if(valore[0]=="-"){
			$('td',row).eq(4).addClass('text-danger');
		} else if(valore[2]!=0){
			$('td',row).eq(4).addClass('text-success');
		}
	},
	"footerCallback": function ( row, data, start, end, display ) {
		var api = this.api(), data;

		// Remove the formatting to get integer data for summation
		var intVal = function(i) {
			return typeof i === 'string' ? setNumber(i) * 1
					: typeof i === 'number' ? i : 0;
		};
		
		function setNumber(i){
			if(i[0]=="-"){
				i = ("-" + i.substring(3).replace('.','').replace(',','.')) * 1;
			} else {
				i = i.substring(2).replace('.','').replace(',','.') * 1;
			}
			return i;
		};
		
		// Total over all pages
//		totalB = api.column(2).data().reduce(function(a, b) {
//			return intVal(a) + intVal(b);
//		}, 0);

		// Total over this page
		pageTotalB = api.column(2, {
			page : 'current'
		}).data().reduce(function(a, b) {
			return intVal(a) + intVal(b);
		}, 0);

		// Update footer
		$(api.column(2).footer()).html(
				setCurrency(pageTotalB) 
//				+ ' <br> (' + setCurrency(totalB) + ' totale)'
				);// Total over all pages
		
//		totalBS = api.column(3).data().reduce(function(a, b) {
//			return intVal(a) + intVal(b);
//		}, 0);

		// Total over this page
		pageTotalBS = api.column(3, {
			page : 'current'
		}).data().reduce(function(a, b) {
			return intVal(a) + intVal(b);
		}, 0);

		// Update footer
		$(api.column(3).footer()).html(
				setCurrency(pageTotalBS) 
//				+ ' <br> (' + setCurrency(totalBS) + ' totale)'
				);

		// Total over all pages
//		total = api.column(4).data().reduce(function(a, b) {
//			return intVal(a) + intVal(b);
//		}, 0);

		// Total over this page
		pageTotal = api.column(4, {
			page : 'current'
		}).data().reduce(function(a, b) {
			return intVal(a) + intVal(b);
		}, 0);

		// Update footer
		if(pageTotal>0){
			$(api.column(4).footer()).html(
					"<span class=\"text-success\">"+setCurrency(pageTotal)+"</span>" 
//					+ ' <br> (' + setCurrency(total) + ' totale)'
					);
		} else if(pageTotal<0){
			$(api.column(4).footer()).html(
					"<span class=\"text-danger\">"+setCurrency(pageTotal)+"</span>" 
//					+ ' <br> (' + setCurrency(total) + ' totale)'
					);
		} else {
			$(api.column(4).footer()).html(
					setCurrency(pageTotal) 
//					+ ' <br> (' + setCurrency(total) + ' totale)'
					);
		}
		
		
		function setCurrency(valore){
			var ris = valore>=0? "&euro; ": "-&euro; ";
		    valore = valore>=0? valore.toFixed(2) : ("" + valore.toFixed(2)).substring(1);
		    var temp = "," + valore[valore.length-2] + valore[valore.length-1];
		    var count = 3;
		    for(var i = valore.length-4 ;i>=0; i--){
		      if(count==0){
		        temp = "." + temp;
		        count = 3;
		      }
		      temp = valore[i] + temp;
		      count--;

		    }
		    ris = ris + temp;
		    return ris;
		}
	}
	  
	  
});

$('.mydatatablenosearch').DataTable({
	"searching": false,
	scrollY: 283,
	scrollX: true,
	scrollCollapse: true,
	paging: false,
	"language": {
	    "search": "Cerca:",
		"zeroRecords": "Nessun risultato da mostrare",
		"info":           "Mostrati _TOTAL_ risultati",
		"infoEmpty":      "Mostrati 0 risultati",
		"infoFiltered":   "(filtrati su un totale di _MAX_ risultati)"
	  },
});