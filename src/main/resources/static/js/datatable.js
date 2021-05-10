$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		      { "mData": "name" },
				  { "mData": "EmployeeId" },
				  { "mData": "Name" },
				  { "mData": "Job" },
				  { "mData": "Salary" },
				  { "mData": "Begin Date" },
				  { "mData": "New salary" }
			]
	 })
});