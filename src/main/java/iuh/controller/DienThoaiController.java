package iuh.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import iuh.dao.DienThoaiDAO;
import iuh.dao.NhaCungCapDAO;
import iuh.dao.impl.DienThoaiImpl;
import iuh.dao.impl.NhaCungCapImpl;
import iuh.entity.DienThoai;
import iuh.entity.NhaCungCap;
import iuh.util.EntityManagerFactoryUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Servlet implementation class DienThoaiController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet(urlPatterns = { "/dien-thoai" })
public class DienThoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String UPLOAD_DIRECTORY = "D:/IUH/IUB_LTWWW/Labs/21129321_Lab05/src/main/webapp/uploads";

	private EntityManagerFactoryUtil entityManagerFactoryUtil;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DienThoaiController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		entityManagerFactoryUtil = new EntityManagerFactoryUtil();
		this.dienThoaiDAO = new DienThoaiImpl(entityManagerFactoryUtil.getEnManager());
		this.nhaCungCapDAO = new NhaCungCapImpl(entityManagerFactoryUtil.getEnManager());

		File fileDir = new File(UPLOAD_DIRECTORY);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";

		switch (action) {
		case "form":
			showForm(request, response);
			break;
		case "insert":
			insertDienThoai(request, response);
			break;
		case "delete":
			deleteDienThoai(request, response);
			break;
		case "search":
			searchDienThoai(request, response);
			break;
		case "filter":
			filterByNhaCungCap(request, response);
			break;
		default:
			showList(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<DienThoai> list = dienThoaiDAO.findAll();
		List<NhaCungCap> listNCC = nhaCungCapDAO.findAll();
		request.setAttribute("listDT", list);
		request.setAttribute("listNCC", listNCC);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/dien-thoai/list.jsp");
		dispatcher.forward(request, response);
	}

	private void filterByNhaCungCap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int nhaCungCapId = Integer.parseInt(request.getParameter("nhaCungCap"));
		
		List<DienThoai> filteredList = dienThoaiDAO.findByNhaCungCap(nhaCungCapId);
		List<NhaCungCap> listNCC = nhaCungCapDAO.findAll();
		
		request.setAttribute("listDT", filteredList);
		request.setAttribute("listNCC", listNCC);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/dien-thoai/list.jsp");
		dispatcher.forward(request, response);
	}

	private void searchDienThoai(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword.isEmpty()) {
			showList(request, response);
			return;
		}
		List<DienThoai> resultList = dienThoaiDAO.searchDienThoai(keyword.toLowerCase().toString());
		List<NhaCungCap> listNCC = nhaCungCapDAO.findAll();

		request.setAttribute("listDT", resultList);
		request.setAttribute("listNCC", listNCC);

		RequestDispatcher dispatcher = request.getRequestDispatcher("views/dien-thoai/list.jsp");
		dispatcher.forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NhaCungCap> listNCC = this.nhaCungCapDAO.findAll();
		request.setAttribute("listNCC", listNCC);
		
		String idString = request.getParameter("id");
		if (idString != null && !idString.equals("")) {
			DienThoai dt = this.dienThoaiDAO.findById(Integer.parseInt(idString));
			request.setAttribute("dt", dt);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/dien-thoai/form.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/dien-thoai/form.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void insertDienThoai(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maNCC = Integer.valueOf(request.getParameter("maNCC"));
		String tenDT = request.getParameter("tenDT");
		String nsxInput = request.getParameter("namSanXuat");
		int namSanXuat = Integer.valueOf(nsxInput.length() > 1 ? nsxInput : "0");
		String cauHinh = request.getParameter("cauHinh");

		Part hinhAnhPart = request.getPart("hinhAnh");
		String fileName = hinhAnhPart.getSubmittedFileName();
		hinhAnhPart.write(UPLOAD_DIRECTORY + File.separator + fileName);

		NhaCungCap nhaCungCap = this.nhaCungCapDAO.findById(maNCC);
		DienThoai dienThoai = new DienThoai(tenDT, namSanXuat, cauHinh, nhaCungCap, fileName);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<DienThoai>> violations = validator.validate(dienThoai);

		if (!violations.isEmpty()) {
			StringBuilder errorMessages = new StringBuilder();
			violations.forEach(violation -> {
				errorMessages.append(violation.getMessage()).append("<br>");
			});
			request.setAttribute("errorMessages", violations);
			showForm(request, response);
			return;
		}

		System.out.println(dienThoai);
		dienThoaiDAO.save(dienThoai);
		showList(request, response);
	}

	private void deleteDienThoai(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		this.dienThoaiDAO.delete(id);
		showList(request, response);
	}

}
