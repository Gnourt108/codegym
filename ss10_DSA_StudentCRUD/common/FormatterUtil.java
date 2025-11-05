package ss10_DSA_StudentCRUD.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatterUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String dateStr, LocalDate defaultDate) {
        if (dateStr == null || dateStr.isEmpty()) return defaultDate;
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (Exception e) {
            System.out.println("Ngày không hợp lệ, giữ nguyên giá trị cũ.");
            return defaultDate;
        }
    }
}
