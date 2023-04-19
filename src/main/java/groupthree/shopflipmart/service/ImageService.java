package groupthree.shopflipmart.service;

import groupthree.shopflipmart.dto.ImageDTO;
import groupthree.shopflipmart.entity.Image;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.repository.ImageRepository;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements ImageServiceImp {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public boolean saveImage(String src, Product product) {
        Image image = new Image();
        image.setSrc(src);
        image.setProduct(product);

        try {
            imageRepository.save(image);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<ImageDTO> getAllImage() {
        List<Image> imageList = imageRepository.findAll();
        List<ImageDTO> imageDTOList = new ArrayList<>();

        for (Image image:imageList){
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.getImageDTO(image);
            imageDTOList.add(imageDTO);
        }
        return imageDTOList;
    }

    @Override
    public List<ImageDTO> getAllImageGroupByProduct() {
        List<Image> imageList = imageRepository.getAllImageGroupByProductId();

        List<ImageDTO> imageDTOList = new ArrayList<>();

        for (Image image:imageList){
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.getImageDTO(image);
            imageDTOList.add(imageDTO);
        }
        return imageDTOList;
    }

    @Override
    public List<Image> getAllImageByProduct(Product product) {
        return imageRepository.findAllByProduct(product);
    }

    @Override
    public Image getFirstImageByProduct(Product product) {
        return imageRepository.findFirstByProduct(product);
    }
}
