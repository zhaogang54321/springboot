<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.pageDetail {
		display: none;
    }
    .show {
		display: table-row;
	}
</style>
<script>
	$(function () {
		$('#list').click(function () {
			$('.pageDetail').toggleClass('show');
		});
	});
</script>
</head>
<body>
	<h1 align="center">学生数据</h1>
	<div align="center">
		<table width="80%" border="0">
			<tr>
				<td>
					<button type="button" onclick="addpage()">增加一条</button>
				</td>
			</tr>
		</table>
		<form action="${pageContext.request.contextPath}/student/query.action" method="post">
            <table class="gridtable" style="width:80%;">
                <tr>
                    <th>页码：</th>
                    <td><input type="text" name="page" value="${page}"/></td>
                    <th>页面大小：</th>
                    <td><input type="text" name="rows" value="${rows}"/></td>
                    <td ><input type="submit" value="查询"/></td>
                </tr>
            </table>
        </form>
		<table class="gridtable" style="width:80%;">
			<tr>
                <th colspan="2">分页信息 - [<a href="javascript:;" id="list">展开/收缩</a>]</th>
            </tr>
            <tr class="pageDetail">
                <th style="width: 300px;">当前页号</th>
                <td>${pageInfo.pageNum}</td>
            </tr>
            <tr class="pageDetail">
                <th>页面大小</th>
                <td>${pageInfo.pageSize}</td>
            </tr>
            <tr class="pageDetail">
                <th>起始行号(>=)</th>
                <td>${pageInfo.startRow}</td>
            </tr>
            <tr class="pageDetail">
                <th>终止行号(<=)</th>
                <td>${pageInfo.endRow}</td>
            </tr>
            <tr class="pageDetail">
                <th>总结果数</th>
                <td>${pageInfo.total}</td>
            </tr>
            <tr class="pageDetail">
                <th>总页数</th>
                <td>${pageInfo.pages}</td>
            </tr>
            <tr class="pageDetail">
                <th>第一页</th>
                <td>${pageInfo.firstPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>前一页</th>
                <td>${pageInfo.prePage}</td>
            </tr>
            <tr class="pageDetail">
                <th>下一页</th>
                <td>${pageInfo.nextPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>最后一页</th>
                <td>${pageInfo.lastPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否为第一页</th>
                <td>${pageInfo.isFirstPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否为最后一页</th>
                <td>${pageInfo.isLastPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否有前一页</th>
                <td>${pageInfo.hasPreviousPage}</td>
            </tr>
            <tr class="pageDetail">
                <th>是否有下一页</th>
                <td>${pageInfo.hasNextPage}</td>
            </tr>
        </table>
	</div>
	<div align="center">
		<table width="80%" border="1">
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>操作</th>
			</tr>
			<c:forEach var="t" items="${pageInfo.list }" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${t.name }</td>
				<td>${t.age }</td>
				<td>
					<a  href="javascript:editpage('${t.id }')">修改</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a  href="javascript:deleteOne('${t.id }')">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
		
        <table class="gridtable" style="width:80%;text-align: center;">
            <tr>
                <c:if test="${pageInfo.hasPreviousPage}">
                    <td>
                        <a href="${pageContext.request.contextPath}/student/query.action?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">前一页</a>
                    </td>
                </c:if>
                <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
                    <c:if test="${nav == pageInfo.pageNum}">
                        <td style="font-weight: bold;">${nav}</td>
                    </c:if>
                    <c:if test="${nav != pageInfo.pageNum}">
                        <td>
                            <a href="${pageContext.request.contextPath}/student/query.action?page=${nav}&rows=${pageInfo.pageSize}">${nav}</a>
                        </td>
                    </c:if>
                </c:forEach>
                <c:if test="${pageInfo.hasNextPage}">
                    <td>
                        <a href="${pageContext.request.contextPath}/student/query.action?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}	">下一页</a>
                    </td>
                </c:if>
            </tr>
        </table>
	</div>
</body>
<script type="text/javascript">
	function editpage(id){
		location.href = "<%=request.getContextPath()%>/student/editpage/"+id+".action";
	}
	function deleteOne(id){
		if(confirm("确定要删除吗？")){
			location.href = "<%=request.getContextPath()%>/student/deleteOne/"+id+".action";
		}
	}
	function addpage(){
		location.href = "<%=request.getContextPath()%>/student/addpage.action";
	}
</script>
</html>