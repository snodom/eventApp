package pl.studentsevent.studentseventapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.studentsevent.studentseventapp.model.event.Category;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    //Category findByCategory_id(Long category_id);
  //  Category
}
