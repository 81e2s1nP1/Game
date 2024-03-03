package com.pa.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pa.dto.AuthorDTO;
import com.pa.dto.GameDTO;
import com.pa.dto.TypeDTO;

public class UpdateGameRequest {

    @Valid
    @NotNull(message = "GameDTO is required")
    private GameDTO gameDTO;

    @Valid
    @NotNull(message = "AuthorDTO is required")
    private AuthorDTO authorDTO;

    @Valid
    @NotNull(message = "TypeDTO is required")
    private TypeDTO typeDTO;

    public UpdateGameRequest() {
    }

    public UpdateGameRequest(@Valid @NotNull(message = "GameDTO is required") GameDTO gameDTO,
                         @Valid @NotNull(message = "AuthorDTO is required") AuthorDTO authorDTO,
                         @Valid @NotNull(message = "TypeDTO is required") TypeDTO typeDTO) {
        this.gameDTO = gameDTO;
        this.authorDTO = authorDTO;
        this.typeDTO = typeDTO;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public TypeDTO getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(TypeDTO typeDTO) {
        this.typeDTO = typeDTO;
    }
}

