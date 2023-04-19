package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.dto.ImageDTO;
import groupthree.shopflipmart.entity.Image;
import groupthree.shopflipmart.entity.Product;

import java.util.List;

public interface ImageServiceImp {

    boolean saveImage(String src, Product product);

    List<ImageDTO> getAllImage();

    List<ImageDTO> getAllImageGroupByProduct();

    List<Image> getAllImageByProduct(Product product);

    Image getFirstImageByProduct(Product product);
}
