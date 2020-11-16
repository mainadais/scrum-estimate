package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.entity.Vote;
import com.dais.scrum.estimate.service.VotesPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    final VotesPublisher publisher;

    public Publisher<ListResult<Vote>> voting() {
        Observable<ListResult<Vote>> observable = Observable.create(publisher::setEmitter);
        ConnectableObservable<ListResult<Vote>> connect = observable.publish();
        connect.connect();
        return connect.toFlowable(BackpressureStrategy.BUFFER);
    }
}
