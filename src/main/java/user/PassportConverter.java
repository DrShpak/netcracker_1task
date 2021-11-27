package user;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class PassportConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) {
        return new Passport(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1]));
    }
}
