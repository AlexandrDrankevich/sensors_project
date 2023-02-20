package by.it.controllers;

import by.it.dto.MeasurementDTO;
import by.it.models.Measurement;
import by.it.services.MeasurementService;
import by.it.util.MeasurementErrorResponse;
import by.it.util.MeasurementNotSavedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.getMeasurements().stream().map(x -> modelMapper.map(x, MeasurementDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getMeasurements().stream().filter(Measurement::isRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
          if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getField())
                        .append(":").append(fieldError.getDefaultMessage() == null ? fieldError.getCode() : fieldError
                                .getDefaultMessage());
            }
              System.out.println(stringBuilder.toString());
            throw new MeasurementNotSavedException(stringBuilder.toString());
        }
        measurementService.addMeasurement(modelMapper.map(measurementDTO, Measurement.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotSavedException measurementNotSavedException) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(measurementNotSavedException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}


