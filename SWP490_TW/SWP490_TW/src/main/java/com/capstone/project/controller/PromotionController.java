package com.capstone.project.controller;

import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/promotions")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<?> getAllPromotion() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, promotionService.getAllPromotion()));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PromotionRequest request) {
        boolean checkPromotionExist = promotionService.checkPromotion(request.getPromotionCode());
        if (checkPromotionExist == false) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Promotion is exist", false, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, promotionService.createPromotion(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PromotionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, promotionService.updatePromotion(id, request)));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getPromotionByCode(@PathVariable("code") String code) {
        boolean checkPromotionExist = promotionService.checkPromotion(code);
        if (checkPromotionExist == false) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Promotion is not exist", false, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, promotionService.getPromotionByCode(code)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        boolean checkPromotionExist = promotionService.getPromotionById(id);
        if (checkPromotionExist == true) {
            promotionService.deletePromotion(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Promotion ok", true, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Error", false, "null"));
    }

    @GetMapping("/searchPromotion/{promotionCode}")
    public ResponseEntity<?> searchProductByName(@PathVariable("promotionCode") String promotionCode) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, promotionService.searchPromotionByCode(promotionCode))
        );
    }

}
