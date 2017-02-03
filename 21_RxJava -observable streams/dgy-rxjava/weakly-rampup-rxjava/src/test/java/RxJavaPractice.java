import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class RxJavaPractice {

  private static final Integer ___ = 0;
  private static final Object ____ = new Object();
  private static final String _____ = "";

  @Test(dataProvider = "testObservableProvider")
  public void p1(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValueCount(___);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p2(Observable<Integer> input) {
    TestSubscriber<List<Integer>> testSubscriber = TestSubscriber.create();
    input.toList().subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValueCount(1);
    assertThat(testSubscriber.getOnNextEvents()).containsExactly(____);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p3(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValues(1, 4, 9, 16, 25);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p4(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.take(3).subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValues(___);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p5(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.scan((sum, value) -> sum + value).subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValues(___);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p6(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValues(4, 5, 4, 5);
  }

  @Test(dataProvider = "testObservableProvider")
  public void p7(Observable<Integer> input) {
    TestSubscriber<Integer> testSubscriber = TestSubscriber.create();
    input.subscribe(testSubscriber);
    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertValues(2, 4);
  }

  @DataProvider(name = "testObservableProvider")
  public Object[][] parameterObservableProvider() {
    return new Object[][] {
        {Observable.range(1, 5)}
    };
  }
}
