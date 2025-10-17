package esvar.ua.generate.footer;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import esvar.ua.generate.Signature;
import esvar.ua.generate.header.NumericHeader;
import esvar.ua.settings.FontLoader;

import java.io.IOException;

public class ForFMC {
    public static Div generate(String examiner, Table lastRowTable, boolean numericHeader) throws IOException {

        if (numericHeader) return new Div()
                    .add(NumericHeader.addNumericHeader(lastRowTable, 5))
                    .add(new Paragraph(""))
                    .add(Signature.generateSignature(examiner))
                    .setKeepTogether(true);
        else return new Div()
                .add(lastRowTable)
                .add(new Paragraph(""))
                .add(Signature.generateSignature(examiner))
                .setKeepTogether(true);


    }
}
