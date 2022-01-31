package hu.progmatic.kalandjatek.character;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KalandjatekService {

  public Race getResults(Answer answer) {
    Map<Race, Integer> answerEvaluation = new HashMap<>();
    fillEvaluationMap(answerEvaluation, answer.race1);
    fillEvaluationMap(answerEvaluation, answer.race2);
    fillEvaluationMap(answerEvaluation, answer.race3);
    fillEvaluationMap(answerEvaluation, answer.race4);
    fillEvaluationMap(answerEvaluation, answer.race5);
    fillEvaluationMap(answerEvaluation, answer.race6);
    fillEvaluationMap(answerEvaluation, answer.race7);
    fillEvaluationMap(answerEvaluation, answer.race8);
    return getMaxKey(answerEvaluation);
  }

  private Race getMaxKey(Map<Race, Integer> answerEvaluation) {
    int maxValue = 0;
    Race maxKey = null;
    for (Map.Entry<Race, Integer> entry : answerEvaluation.entrySet()) {
      if (maxValue < entry.getValue()) {
        maxValue = entry.getValue();
        maxKey = entry.getKey();
      }
    }
    return maxKey;
  }

  private void fillEvaluationMap(Map<Race, Integer> answerEvaluation, Race race) {
    Integer value = answerEvaluation.getOrDefault(race, 0);
    answerEvaluation.put(race, value + 1);
  }

}
