/*import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.repository.OnskeLampenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RepositoryTest {

    private OnskeLampenRepository onskeLampenRepository;

    @BeforeEach
    void setUp() {
        onskeLampenRepository = new OnskeLampenRepository();
    }

    @Test
    void createWish() {
        OnskeLampen onske = new OnskeLampen(3, "Test", "Test description", 100, 1, "Test supplier");

        //onskeLampenRepository.createWish(onske);
        onskeLampenRepository.showOnsker().add(onske);

        List<OnskeLampen> wishList = onskeLampenRepository.showOnsker();
        assertEquals(3, wishList.size());
        assertEquals(3, wishList.get(2).getId());
        assertEquals("Test", wishList.get(2).getName());
    }

    @Test
    void deleteWish() {
        int id = 1;

        OnskeLampen deletedWish = onskeLampenRepository.deleteWish(id);

        assertEquals(1, deletedWish.getId());
        assertEquals("computer", deletedWish.getName());
    }

    @Test
    void updateWish() {
        OnskeLampen onskeUpdate = new OnskeLampen(2, "Updated Name", "Updated description", 200, 2, "Updated supplier");

        onskeLampenRepository.updatOenske(onskeUpdate);

        OnskeLampen updatedWish = onskeLampenRepository.getWishById(2);
        assertEquals("Updated Name", updatedWish.getName());
        assertEquals("Updated description", updatedWish.getDescription());
    }

    @Test
    void getWishById() {
        int id = 2;

        OnskeLampen wishById = onskeLampenRepository.getWishById(id);

        assertEquals(2, wishById.getId());
        assertEquals("bil", wishById.getName());
    }
}*/
