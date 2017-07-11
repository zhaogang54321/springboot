<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">学生修改页面</h1>
	<div align="center">
		<form name="form1" method="post">
			<input type="hidden" name="id" value="${student.id }">
		<table>
			<tr>
				<th>姓名：</th>
				<td><input name="name" value="${student.name }"></td>
			</tr>
			<tr>
				<th>年龄：</th>
				<td><input name="age" value="${student.age }"></td>
			</tr>
		</table>
		</form>
	</div>
	<div align="center">
		<button type="button" onclick="editsave()">修改保存</button>
	</div>
</body>
<script type="text/javascript">
	function editsave(){
		document.form1.action = "<%=request.getContextPath()%>/student/editsave.action";
		document.form1.submit();
	}
</script>
</html>