<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
  <title>Main page</title>
 </head>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>

 
 <body>
   
   
   	<script type="text/javascript">
      $(function()	{
		   /*
	$('td').click(function(e)	{
		//ловим элемент, по которому кликнули
		var t = e.target || e.srcElement;
		//получаем название тега
		var elm_name = t.tagName.toLowerCase();
		//если это инпут - ничего не делаем
		if(elm_name == 'input')	{return false;}
		var val = $(this).html();
		var code = '<input type="text" id="edit" value="'+val+'" />';
		$(this).empty().append(code);
		$('#edit').focus();
		$('#edit').blur(function()	{
			var val = $(this).val();
			$(this).parent().empty().html(val);
		});
	$(window).keydown(function(event){
	//ловим событие нажатия клавиши
	if(event.keyCode == 13) {	//если это Enter
		$('#edit').blur();	//снимаем фокус с поля ввода
	}
});
	});
});
*/


//при нажатии на ячейку таблицы с классом edit
$('td.edit').click(function(){
//находим input внутри элемента с классом ajax и вставляем вместо input его значение
$('.ajax').html($('.ajax input').val());
//удаляем все классы ajax
$('.ajax').removeClass('ajax');
//Нажатой ячейке присваиваем класс ajax
$(this).addClass('ajax');
//внутри ячейки создаём input и вставляем текст из ячейки в него
$(this).html('<input id="editbox" size="'+ $(this).text().length+'" type="text" value="' + $(this).text().trim() + '" />');
//устанавливаем фокус на созданном элементе
$('#editbox').focus();
});


//определяем нажатие кнопки на клавиатуре
$('td.edit').keydown(function(event){
//получаем значение класса и разбиваем на массив
//в итоге получаем такой массив - arr[0] = edit, arr[1] = наименование строки, arr[2] = id столбца
arr = $(this).attr('class').split( " " );
console.log(arr);
console.log(event.which);
//проверяем какая была нажата клавиша и если была нажата клавиша Insert (код 45)
if(event.which == 45)
   {
	   console.log("+");
   }
if(event.which == 16)
   {
	
	console.log("yse");

   }
//проверяем какая была нажата клавиша и если была нажата клавиша Enter (код 13)
   if(event.which == 13)
   {
//получаем наименование таблицы, в которую будем вносить изменения
var table = $('table').attr('id');
//выполняем ajax запрос методом POST
console.log(table);
console.log($('.ajax input').val());
 $.ajax({ type: "POST",
//в файл update_cell.php
 url:"add?action=saveData",
//создаём строку для отправки запроса
//value = введенное значение
//id = номер строки
//field = название столбца
//table = собственно название таблицы
 data: "value="+$('.ajax input').val()+"&id="+arr[2]+"&field="+arr[1]+"&table="+table,
//при удачном выполнении скрипта, производим действия
 success: function(data){
//находим input внутри элемента с классом ajax и вставляем вместо input его значение
 $('.ajax').html($('.ajax input').val());
//удаялем класс ajax
 $('.ajax').removeClass('ajax');
 }});
 }});
 $(document).on('blur', '#editbox', function(){
$('.ajax').html($('.ajax input').val());
$('.ajax').removeClass('ajax');
});
});
    </script>
   
   
   
   
   
   

     <% 
      int[][] arr = (int[][])(request.getAttribute("arr"));
      int[][] arrc=arr ;     
	  String[][] Tabs = (String[][])(request.getAttribute("Tabs"));  
	  String referrer = (String)(request.getAttribute("referrer"));  
     %>   
	 <table border="1px" id="clients">
	 <tbody>
	<% 
	for (int i = 0; i < Tabs.length; i++)
		{
	%>
	<tr>
			<%
			for (int j = 0; j < 3; j++)
			{
			%>
			<td class="edit <%=i%> <%=j%>">
     <%= Tabs[i][j]%>
	 </td>
	 <% 
	 			}
				%>
				</tr>
				<% 
		}
	%>
	</tbody>
	</table>
	<%= referrer%>
	

	
  <form method="post" action="add">   
     <input type="submit" value="add"></td>
	 <input type="text" name="text1">
	 <input type="text" name="text2">
	 <input type="text" name="text3">
  </form>

  <form method="post" action="del">       
     <input type="submit" value="del"></td>
	 <input type="number" name="ndel"> 
  </form>
  
   <form method="post" action="out">       
     <input type="submit" value="out"></td>
  </form>
 </body>

</html>