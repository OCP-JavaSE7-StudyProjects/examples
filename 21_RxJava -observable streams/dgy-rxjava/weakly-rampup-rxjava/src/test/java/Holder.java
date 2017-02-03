class Holder<T> {
  private T value;

  private Holder() { }

  private Holder(T value) {
    setValue(value);
  }

  static <T> Holder<T> create() {
    return new Holder<>();
  }

  static <T> Holder<T> create(T value) {
    return new Holder<>(value);
  }

  T getValue() {
    return value;
  }

  void setValue(T value) {
    this.value = value;
  }
}
