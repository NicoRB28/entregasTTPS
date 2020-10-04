package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PaseVirtual
 */
@WebServlet("/PaseVirtual")
public class PaseVirtual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaseVirtual() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		DatosPase datos = (DatosPase) session.getAttribute("datos");
		String nombre = datos.getNombre();
		String apellido = datos.getApellido();
		String mail = datos.getMail();
		String dni = datos.getDni();
		String show = datos.getShow();
		Random rand = new Random();
		Integer numero = rand.nextInt(25);
		
		OutputStream outputStream = response.getOutputStream();
		BufferedImage image = new BufferedImage(1400, 700, BufferedImage.TYPE_INT_BGR);
		
		Graphics2D graphics = image.createGraphics();
		graphics.clearRect(0, 0, 1024,800);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 14));
		graphics.setColor(Color.WHITE);
		graphics.drawString("Codigo"+numero + "\n", 30, 30);
		graphics.drawString(nombre + " " + apellido + "\n", 30, 45);
		graphics.drawString("DNI: "+ dni + "\n", 30, 60);
		graphics.drawString(mail, 30, 80);
		String path = null;
		switch(show) {
		case "soda":
			path = "/WEB-INF/img/soda.jpg";
			break;
		case "rusia":
			path = "/WEB-INF/img/rusia.jpg";
			break;
		case "charco":
			path = "/WEB-INF/img/cruzando.jpg";
			break;
		}
		
		BufferedImage img = ImageIO.read(this.getServletContext().getResourceAsStream(path));
		graphics.drawImage(img,100,100, null, null);
		javax.imageio.ImageIO.write(image, "jpeg", outputStream);
		outputStream.close();
		response.setContentType("image/jpeg");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
