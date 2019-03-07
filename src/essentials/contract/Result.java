package essentials.contract;

import java.util.function.Consumer;

/**
 * Creator: Patrick
 * Created: 07.03.2019
 * Purpose:
 */
public class Result<D> {
    private final D _data;
    private final boolean _error;

    private Result(D data, boolean error) {
        _data = data;
        _error = error;
    }

    public D getData() {
        return _data;
    }

    public boolean isOk() {
        return !_error;
    }

    public boolean isError() {
        return !_error;
    }

    public void ifOk(Consumer<D> consumer) {
        if (!_error) {
            consumer.accept(_data);
        }
    }

    public void ifError(Consumer<D> consumer) {
        if (_error) {
            consumer.accept(_data);
        }
    }

    public static <Data> Result<Data> ok(Data data) {
        return new Result<>(data, false);
    }

    public static <Data> Result<Data> error(Data data) {
        return new Result<>(data, true);
    }
}
