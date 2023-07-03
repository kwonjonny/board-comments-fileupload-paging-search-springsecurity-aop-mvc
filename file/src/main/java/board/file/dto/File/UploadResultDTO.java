package board.file.dto.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {

    private String uuid;
    private String fileName;
    private boolean img;

    public String getLink() {
        if(img) {
            return "s_"+uuid+"_"+fileName;
        } else {
            return "default.jpg";
        }

    }
    
}
