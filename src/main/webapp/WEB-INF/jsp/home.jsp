<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
  <meta charset="UTF-8">
	<title>大卖场演示页面</title>
	<style type="text/css">
	h4.block {
		font-size: 17px;
		font-family: "Open Sans", sans-serif;
    	font-weight: 300;
	}
	.table-bordered {
	    border: 1px solid #ddd;
	}
	.table {
	    width: 100%;
	    max-width: 100%;
	    margin-bottom: 20px;
	}
	table {
	    background-color: transparent;
	}
	table {
	    border-spacing: 0;
	    border-collapse: collapse;
	}
	thead {
	    display: table-header-group;
	    vertical-align: middle;
	    border-color: inherit;
	}
	tbody {
	    display: table-row-group;
	    vertical-align: middle;
	    border-color: inherit;
	}
	.table-striped>tbody>tr:nth-of-type(odd) {
	    background-color: #f9f9f9;
	}
	.table thead tr th {
	    font-size: 14px;
	    font-weight: 600;
	}
	.table-bordered>thead>tr>th {
	    border: 1px solid #ddd;
	}
	.table>thead>tr>th {
	    padding: 8px;
	    line-height: 1.42857143;
	}
.table-bordered>tbody>tr>td, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>td, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>thead>tr>th {
    border: 1px solid #ddd;
}
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
    padding: 8px;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
</head>
<body>
<div style="text-align: center;">
	<img src="http://153.99.46.202:33905/M00/00/0A/rBAKI1aCSJWAGFbrAAGGazsB5WA330.jpg" height="80px"> 演示
</div>
<hr>
<div style="padding-left: 100px;">
	<h4 class="block">用户列表</h4>
	<table class="table table-bordered table-striped" style="width:1000px;">
	<thead>
	<tr>
		<th>
			 用户名
		</th>
		<th>
			 邮箱
		</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			 ${name}
		</td>
		<td>
			${email}
		</td>
	</tr>
	
	</tbody>
	</table>
</div>

</body>
</html>
<%-- 
<body>
	<c:url value="/resources/text.txt" var="url"/>
	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	Hello <b>${name}</b>, Welcome to SaaS ERP
	<br><br>
	SSO login successfully, followings are your information:<br><br>
	UserId: <b>${userid}</b> <br>
	Email: <b>${email}</b>
	
</body> --%>

</html>
