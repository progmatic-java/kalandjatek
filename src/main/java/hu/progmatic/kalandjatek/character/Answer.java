package hu.progmatic.kalandjatek.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
  public static final String PLEASE_ANSWER_THE_QUESTION = "Please answer the question";
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @NotEmpty(message = "Please choose a name")
  String name;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race1;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race2;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race3;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race4;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race5;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race6;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race7;
  @NotNull(message = PLEASE_ANSWER_THE_QUESTION)
  Race race8;
}
