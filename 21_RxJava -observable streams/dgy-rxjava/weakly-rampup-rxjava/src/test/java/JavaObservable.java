import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.google.common.truth.Truth.assertThat;

public class JavaObservable {

  @Test public void testName() {
    List<Object> consumedEvents = new ArrayList<>();
    Observer myObserver = (o, arg) -> consumedEvents.add(arg);
    Observable myObservable = new TestObservable();

    myObservable.notifyObservers();
    myObservable.notifyObservers(Event.EVENT1);

    myObservable.addObserver(myObserver);
    assertThat(myObservable.countObservers()).isEqualTo(1);

    myObservable.notifyObservers();
    myObservable.notifyObservers(Event.EVENT2);
    myObservable.deleteObserver(myObserver);

    assertThat(myObservable.countObservers()).isEqualTo(0);
    assertThat(consumedEvents).containsExactly(null, Event.EVENT2);
  }

  private class TestObservable extends Observable {
    @Override public void notifyObservers(Object arg) {
      setChanged();
      super.notifyObservers(arg);
    }
  }

  private enum Event {
    EVENT1,
    EVENT2
  }
}
