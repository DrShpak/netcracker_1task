package validator;

import contracts.Contract;
import contracts.digitalTV.DigitalTVContract;
import contracts.mobileInternet.MobileInternetContract;
import contracts.wiredInternet.WiredInternetContract;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Validator {
    private Contract contract;
    private Status status;
    private String msg;

    public Validator(Contract contract) {
        this.contract = contract;
    }

    public Validator() {
    }

    public Validator(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Validator(Status status) {
        this.status = status;
    }


    /**
     *
     * @return Validator with Status.OK if it's or Status.ERROR  and error message if not okay
     */
    public Validator validate() {
        var validator = validateContract();
        if (validator.status.equals(Status.OK)) {
            if (contract.getClass().equals(DigitalTVContract.class)) {
                return validateDigitalContract();
            }
            if (contract.getClass().equals(MobileInternetContract.class)) {
                return validateMobileInternetContract();
            }
            if (contract.getClass().equals(WiredInternetContract.class)) {
                return validateWiredInternetContract();
            } else {
                return validator;
            }
        } else {
            return validator;
        }
    }

    /**
     * validate only WiredInternetContract fields
     * @return Validator with Status.OK if it's or Status.ERROR  and error message if not okay
     */
    private Validator validateWiredInternetContract() {
        var wiContract = (WiredInternetContract) contract;
        if (wiContract.getSpeedOfCon() < 0) {
            return new Validator(Status.ERROR, "Speed of connection cannot be less than 0");
        } else {
            return new Validator(Status.OK);
        }
    }

    /**
     * validate only MobileInternetContract fields
     * @return Validator with Status.OK if it's or Status.ERROR  and error message if not okay
     */
    private Validator validateMobileInternetContract() {
        var miContract = (MobileInternetContract) contract;
        if (miContract.getMinutes() < 0) {
            return new Validator(Status.ERROR, "Minutes cannot be less than 0");
        } else if (miContract.getNetTraffic() < 0) {
            return new Validator(Status.ERROR, "Traffic cannot be less than 0");
        } else if (miContract.getSms() < 0) {
            return new Validator(Status.ERROR, "Sms cannot be less than 0");
        } else {
            return new Validator(Status.OK);
        }
    }

    /**
     * Nothing to validate. Field TVBundle validates on the parsing stage by opencsv
     * So if opencsv doesn't throw exception it means that ENUM field was valid
     * @return Validator
     */
    private Validator validateDigitalContract() {
        //todo
        return validateContract();
    }

    /**
     * Validate general fields of all contracts
     * @return Validator with Status.OK if it's or Status.ERROR  and error message if not okay
     */
    private Validator validateContract() {
        var validator = new Validator();
        if (contract.getUser().getAge() < 18) {
            validator.setStatus(Status.ERROR);
            validator.setMsg("Age is under 18 y.o.");
            return validator;

        } else if (contract.getPinNumber() < 0) {
            validator.setStatus(Status.ERROR);
            validator.setMsg("Pin number can't be a negative number");
            return validator;

        } else if (contract.getStartDate().isAfter(contract.getEndDate())) {
            validator.setStatus(Status.ERROR);
            validator.setMsg("Invalid date.");
            return validator;

        } else {
            return new Validator(Status.OK);
        }
    }
}
