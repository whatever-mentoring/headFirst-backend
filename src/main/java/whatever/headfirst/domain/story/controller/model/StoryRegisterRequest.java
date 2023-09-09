package whatever.headfirst.domain.story.controller.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryRegisterRequest {

    @NotBlank  // "" , " " , null
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

}
