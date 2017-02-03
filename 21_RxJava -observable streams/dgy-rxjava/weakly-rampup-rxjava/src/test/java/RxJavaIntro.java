import org.testng.annotations.Test;
import rx.*;
import rx.observers.TestObserver;
import rx.observers.TestSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;

public class RxJavaIntro {

  //<editor-fold desc="Write custom observable and observer">
  @Test public void customObservableAndObserver() {
    // Observable contract:
    //   onNext* -> onCompleted|onError
    // Our Observable emits "Hello, world!" then completes
    Observable<String> myObservable = Observable.create(
        new Observable.OnSubscribe<String>() {
          @Override public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello World!");
            subscriber.onCompleted(); // NOTE: required!
            // NOTE: unsubscription, backpressure
          }
    });
    // Create a Observer/Subscriber to consume the data:
    Holder<Boolean> completed = Holder.create(false);
    List<String> consumedString = new ArrayList<>();
    // NOTE: Subscriber is preferred... Subscriber<String> mySubscriber = new Subscriber<String>() {
    Observer<String> myObserver = new Observer<String>() {
      @Override public void onCompleted() { completed.setValue(true); }
      @Override public void onError(Throwable e) { }
      @Override public void onNext(String s) { consumedString.add(s); }
    };
    // we can hook them up to each other using subscribe()
    Subscription subscription = myObservable.subscribe(myObserver);

    assertThat(subscription.isUnsubscribed()).isTrue();
    assertThat(consumedString).containsExactly("Hello World!");
    assertThat(completed.getValue()).isTrue();
  }
  //</editor-fold>

  //<editor-fold desc="Creating Observables. Operators that originate new Observables.">
  @Test(description = "convert an object into an Observable that emits that object")
  public void emitSingleElement() {
    final List<String> consumedString = new ArrayList<>();
    Observable.just("Hello World!").subscribe(consumedString::add); // onNext action
    assertThat(consumedString).containsExactly("Hello World!");
  }

  @Test(description = "convert a set of objects into an Observable that emits those objects, use TestObserver")
  public void emitMultipleElements() {
    TestObserver<Integer> testObserver = new TestObserver<>();
    Observable.just(1, 2).subscribe(testObserver);
    assertThat(testObserver.getOnCompletedEvents()).hasSize(1);
    assertThat(testObserver.getOnNextEvents()).containsExactly(1, 2).inOrder();
  }

  @Test(description = "convert some other object (e.g. iterable) or data structure into an Observable, use TestSubscriber")
  public void createObservableFromIterable() {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    Observable.from(Arrays.asList(1, 2, 3)).subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValueCount(3);
  }

  @Test(description = "create an Observable that emits no items and does not terminate.",
      timeOut = 1000L)
  public void observableThatDoesNothing() {
    TestSubscriber<Object> testSubscriber = TestSubscriber.create();
    try {
      Observable.never().subscribe(testSubscriber);
      //testSubscriber.awaitTerminalEvent(); // NOTE: NEVER!
    } finally {
      testSubscriber.unsubscribe();
    }
    testSubscriber.assertNoTerminalEvent();
  }

  @Test(description = "create an Observable that emits no items but terminates normally.")
  public void emptyObservable() {
    TestSubscriber<Object> testSubscriber = TestSubscriber.create();
    Observable.empty().subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertCompleted();
    testSubscriber.assertNoErrors();
    testSubscriber.assertNoValues(); // No values
  }

  @Test(description = "create an Observable that emits no items and terminates with an error")
  public void errorObservable() {
    TestSubscriber<Object> testSubscriber = TestSubscriber.create();
    Throwable error = new AssertionError();
    Observable.error(error).subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertNotCompleted();
    testSubscriber.assertNoValues();
    testSubscriber.assertError(error); // Error
  }

  @Test(description = "create an Observable that emits a particular item after a given delay",
      timeOut = 100L)
  public void timerTest() {
    TestSubscriber<Long> testSubscriber = TestSubscriber.create();
    Observable.timer(50L, TimeUnit.MILLISECONDS) // scheduler, no backpressure support
        .subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertCompleted();
    testSubscriber.assertValue(0L);
  }
  //</editor-fold>

  //<editor-fold desc="Special Reactive Stream implementations">
  @Test(description = "single class implements the Reactive Pattern for a single value response")
  public void single() {
    TestSubscriber<String> testSubscriber = TestSubscriber.create();
    // http://reactivex.io/documentation/single.html
    Single.just("single") // .subscribe(SingleSubscriber)
        .toObservable()
        .subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertCompleted();
    testSubscriber.assertValueCount(1); // Single
    testSubscriber.assertValue("single");
  }

  @Test(description = "completable represents a deferred computation without any value but"
      + "only indication for completion or exception")
  public void completable() {
    TestSubscriber testSubscriber = TestSubscriber.create();
    Completable.fromAction(() -> {
      // long running operation
    }).subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertUnsubscribed();
    testSubscriber.assertCompleted(); // Completed
    testSubscriber.assertNoValues(); // No values
    testSubscriber.assertNoErrors();
  }
  //</editor-fold>
}
