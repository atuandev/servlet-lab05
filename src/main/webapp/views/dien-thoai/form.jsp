<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script src="https://cdn.tailwindcss.com"></script>
<title>Quản lý điện thoại</title>
</head>
<body>
	<div class="container">
		<%@ include file="../layout/header.jsp"%>
		<%@ include file="../layout/navbar.jsp"%>

		<c:if test="${empty dt}">
			<form action="dien-thoai?action=insert" method="post" enctype="multipart/form-data"
				class="max-w-[800px] p-4 border rounded-md shadow-md mx-auto">
		</c:if>

		<c:if test="${not empty dt}">
			<form action="dien-thoai?action=update" method="post" enctype="multipart/form-data"
				class="max-w-[800px] p-4 border rounded-md shadow-md mx-auto">
		</c:if>
		<table class="table">
			<tr>
				<td><input type="text" hidden value="${dt.maDT }" name="maDT" class="w-full border p-2"></td>
			</tr>
			<tr>
				<th>Mã nhà cung cấp</th>
				<td><select name="maNCC">
						<c:forEach var="ncc" items="${listNCC}">
							<option value="${ncc.maNCC}">${ncc.tenNCC}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Tên</th>
				<td><input type="text" name="tenDT" class="w-full border p-2" value="${dt.tenDT }"></td>
			</tr>
			<tr>
				<th>Năm sản xuất</th>
				<td><input type="number" maxlength="4" name="namSanXuat" class="w-full border p-2"
					value="${dt.namSanXuat }"></td>
			</tr>
			<tr>
				<th>Cấu hình</th>
				<td><input type="text" name="cauHinh" class="w-full border p-2" value="${dt.cauHinh }"></td>
			</tr>
			<tr>
				<th>Hình ảnh</th>
				<td><input type="file" multiple="multiple" name="hinhAnh" class="w-full border p-2"  value="${dt.hinhAnh }"></td>
			</tr>

			<tr>
				<td align="center" colspan="2"><input class="btn btn-primary" type="submit" value="Save"></td>
			</tr>
		</table>
		<p class="text-rose-500">${errorMessages}</p>
		</form>

		<%@ include file="../layout/footer.jsp"%>
	</div>
</body>
</html>