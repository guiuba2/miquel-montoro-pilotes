package com.jagaad.pilotes.model;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ClientDTO toClientDTO(Client client) {
        return MODEL_MAPPER.map(client, ClientDTO.class);
    }

    public List<ClientDTO> toClientDTOList(List<Client> clientsList) {
        return clientsList.stream().map(this::toClientDTO).collect(Collectors.toList());
    }


}
