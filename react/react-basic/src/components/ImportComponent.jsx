import TestComponent from './TestComponent'
import { TempComponent } from './TestComponent';
import TestJavaScript from './TestJavaScript';

export default function ImportComponent() {
    return (
        <div>
            <TestComponent />
            <TempComponent />
            <TestJavaScript />
        </div>
    )
}