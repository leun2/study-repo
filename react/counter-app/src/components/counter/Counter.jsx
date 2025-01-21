import CounterButton from '../button/CounterButton';
import ResetButton from '../button/ResetButton';
import CounterHook from '../../hooks/CounterHook';
import '../../styles/Counter.css';

export default function Counter() {

    const { count, increment, decrement, reset } = CounterHook(0);

    const buttons = [
        { label: "+1", value: 1, action: increment },
        { label: "+3", value: 3, action: increment },
        { label: "+5", value: 5, action: increment },
        { label: "-1", value: 1, action: decrement },
        { label: "-3", value: 3, action: decrement },
        { label: "-5", value: 5, action: decrement },
    ];

    return (
        <div>
            <span className="totalCount">{count}</span>
            <div className="buttonContainer">
                {buttons.map((button, index) => (
                    <CounterButton
                        key={index}
                        label={button.label}
                        onClick={() => button.action(button.value)}
                    />
                ))}
            </div>
            <ResetButton onClick={reset} />
        </div>
    );
}