package com.dais.scrum.estimate.service;

import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.resolver.ListResult;
import io.reactivex.ObservableEmitter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@Setter
public class VotesPublisher implements Consumer<List<Vote>> {

    private ObservableEmitter<ListResult<Vote>> emitter;

    public void setEmitter(ObservableEmitter<ListResult<Vote>> e) {
        this.emitter = e;
    }

    @Override
    public void accept(List<Vote> votes) {
        this.emitter.onNext(new ListResult<>(votes));
    }
}
