package com.example.library.model;

import com.example.library.dto.response.CountRankLoanResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "loan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@SqlResultSetMappings({
        @SqlResultSetMapping( name = "CountRankLoanResponseDTO", classes ={
                @ConstructorResult(
                        targetClass = CountRankLoanResponseDTO.class,
                        columns = {
                                @ColumnResult(name = "bookName"),
                                @ColumnResult(name = "loanCount", type = Integer.class),
                                @ColumnResult(name = "rankLoanBook", type = Integer.class),
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery( name = "Loan.getLoanWithFavoriteAndRank", query =
                "SELECT b.book_name as bookName, " +
                "COUNT(l.book_id) as loanCount, " +
                "ROW_NUMBER() OVER (ORDER BY loanCount DESC) as rankLoanBook " +
                "FROM loan l " +
                "join books b on l.book_id = b.book_id " +
                "group by l.book_id " +
                "order by rankLoanBook "
        ,resultSetMapping = "CountRankLoanResponseDTO"
        )

})
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer loanId;

    Integer userId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable=false, updatable=false)
    Users users;

    Integer bookId;

    @ManyToOne
    @JoinColumn(name = "bookId", insertable=false, updatable=false)
    Books books;

    Date loanDate;

    Date returnDate;

}
