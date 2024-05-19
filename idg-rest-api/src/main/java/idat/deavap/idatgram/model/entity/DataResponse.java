package idat.deavap.idatgram.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

@JsonSerialize
public class DataResponse {
    private int status;
    private String message;
    private Object data;
    
    public DataResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.name();
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * @param httpStatus the httpStatus to set
     */
    public void setStatus(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.name();
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }
}