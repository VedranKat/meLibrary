package springStudy.library.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springStudy.library.dto.BookDto;
import springStudy.library.model.Book;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);

    // OVO !"#$% OD METODA TREBA IZBRISAT I REBUILDAT KAD SE PROMINI ENTITY
    // TAKO DA SE STVORI NOVA IMPLEMENTACIJA !!!

}
