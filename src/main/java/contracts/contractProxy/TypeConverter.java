package contracts.contractProxy;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import contracts.digitalTV.DigitalTVContract;
import contracts.mobileInternet.MobileInternetContract;
import contracts.wiredInternet.WiredInternetContract;

public class TypeConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return switch (s) {
            case "mobile" -> MobileInternetContract.class;
            case "tv" -> DigitalTVContract.class;
            case "wired" -> WiredInternetContract.class;
            default -> throw new RuntimeException();
        };
    }
}
