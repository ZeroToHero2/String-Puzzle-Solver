//-----------------------------------------------------
// Title: TopK Class
// Author: Abdusselam Koç,Bahadır Ünal
// Description: This class represents the TopK object and it’s attributes which are “text” which holds string itself
// and “Frequency” which holds frequency of given string”.
// We need this class for “storing each string’s frequency” in a one instance.
//-----------------------------------------------------
public class TopK implements Comparable<TopK> {
    String text;
    int frequency;

    public TopK(String text, int frequency) {
        this.text = text;
        this.frequency = frequency;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "TopK{" + "text='" + text + '\'' + ", frequency=" + frequency + '}';
    }

    @Override
    public int compareTo(TopK o) {
        if (this.frequency > o.frequency) return 1;
        else if (this.frequency < o.frequency) return -1;
        else return 0;
    }
}
