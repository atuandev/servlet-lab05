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

<title>List dien thoai</title>
</head>
<body>
	<div class="container">
		<%@ include file="../layout/header.jsp"%>
		<%@ include file="../layout/navbar.jsp"%>


		<div class="flex justify-between gap-4 mb-4">
			<form method="post" action="dien-thoai?action=search" class="flex items-center space-x-2">
				<input type="text" name="keyword" placeholder="Tìm kiếm điện thoại"
					class="border p-2 rounded-sm" />
				<button type="submit" class="btn btn-primary">Tìm kiếm</button>
			</form>

			<form method="post" action="dien-thoai" class="flex items-center space-x-2">
				<select name="nhaCungCap" class="border rounded-sm p-2">
					<c:forEach var="ncc" items="${listNCC}">
						<option value="${ncc.maNCC}">${ncc.tenNCC}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary">Lọc</button>
			</form>
		</div>

		<div>
			<table class="table">
				<thead>
					<tr>
						<th>Mã điện thoại</th>
						<th>Tên điện thoại</th>
						<th>Năm sản xuất</th>
						<th>Cấu hình</th>
						<th>Nhà Cung cấp</th>
						<th>Hình ảnh</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${listDT != null}">
							<!-- Nếu listDT không null -->
							<c:forEach var="dt" items="${listDT}">
								<tr>
									<td>${dt.maDT}</td>
									<td>${dt.tenDT}</td>
									<td>${dt.namSanXuat}</td>
									<td>${dt.cauHinh}</td>
									<td>${dt.nhaCungCap.tenNCC}</td>
									<td><img alt="HinhAnh" src="${pageContext.request.contextPath}/uploads/${dt.hinhAnh}"
										width="50"></td>
									<td><a class="text-rose-500 font-semibold"
										href="dien-thoai?action=delete&id=${dt.maDT}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<!-- Nếu listDT null -->
							<tr>
								<td colspan="7">Không có dữ liệu.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<%@ include file="../layout/footer.jsp"%>
	</div>
</body>
</html>