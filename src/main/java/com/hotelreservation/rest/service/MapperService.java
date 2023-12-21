package com.hotelreservation.rest.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapperService{

    private final ModelMapper modelMapper;
    //Normal modelMapper cant map list so we created own modelmapper

    public <T,D> List<D> modelMapper(List<T> source , Class<D> destination){
        List<D> target = new ArrayList<>();
        for( T element : source){
            target.add(modelMapper.map(element,destination));
        }
        return target;
    }
}
