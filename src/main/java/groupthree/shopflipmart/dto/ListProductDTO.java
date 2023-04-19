package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListProductDTO {

    public List<ProductDTO> getProductDTOList(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.getProductDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
