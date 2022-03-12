package com.myos.qrgen.controller;

import com.google.zxing.WriterException;
import com.myos.qrgen.qrcode.QRCodeGenerator;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @GetMapping(path = {"/","/qrcode"})
    public String getQRCode(Model model) {
        String gqpsystem = "https://github.com/gqpsystem";
        String oscargithub = "https://github.com/oscarmy333";

        byte[] image = new byte[0];
        try {

            // Generar y Retornar Código QR en Byte Array
            image = QRCodeGenerator.getQRCodeImage(gqpsystem, 250, 250);

            // Generar y Guardar Imagen de Código QR en la carpeta static/img
            QRCodeGenerator.generateQRCodeImage(oscargithub, 250, 250, QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("gqpsystem", gqpsystem);
        model.addAttribute("oscargithub", oscargithub);
        model.addAttribute("qrcode", qrcode);

        return "qrcode";
    }
}
