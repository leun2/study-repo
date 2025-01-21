import PropTypes from 'prop-types';
import '../../styles/Counter.css';

export default function CounterButton({ label, value, onClick }) {
    return (
        <button
            className="counterButton"
            onClick={() => onClick(value)}
        >
            {label}
        </button>
    );
}

CounterButton.propTypes = {
    label: PropTypes.string.isRequired,
    value: PropTypes.number.isRequired,
    onClick: PropTypes.func.isRequired,
};