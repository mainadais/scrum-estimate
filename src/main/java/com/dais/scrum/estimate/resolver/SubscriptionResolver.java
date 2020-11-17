package com.dais.scrum.estimate.resolver;

import com.dais.scrum.estimate.entity.Vote;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

//    final VotesPublisher publisher;
//
//    public Publisher<ListResult<Vote>> voting() {
//        Observable<ListResult<Vote>> observable = Observable.create(publisher::setEmitter);
//        ConnectableObservable<ListResult<Vote>> connect = observable.publish();
//        connect.connect();
//        return connect.toFlowable(BackpressureStrategy.BUFFER);
//    }

    @PreAuthorize("isAuthenticated()")
    public Publisher<ListResult<Vote>> voting() {
        Observable<ListResult<Vote>> observable = Observable.create(e -> {
            ScheduledExecutorService se = Executors.newScheduledThreadPool(1);
            se.scheduleAtFixedRate(() -> {
                List<Vote> votes = IntStream.range(1, 5).mapToObj(i ->
                        Vote.builder().scrum(UUID.randomUUID().toString())
                                .vote(BigDecimal.valueOf(Math.floor(Math.random() * i))).build()).collect(Collectors.toList());
                e.onNext(new ListResult<>(votes));
            }, 0, 5, TimeUnit.SECONDS);
        });
        ConnectableObservable<ListResult<Vote>> connect = observable.publish();
        connect.connect();
        return connect.toFlowable(BackpressureStrategy.BUFFER);
    }
}
