package etc;

import java.awt.Font;

public enum fuentes 
{
    TITLE_FONT(new Font("Segoe UI", Font.BOLD, 24)),
    MAIN_FONT(new Font("Segoe UI", Font.PLAIN, 14)),
    HEADER_FONT(new Font("Segoe UI", Font.BOLD, 14));
    private final Font font;
    fuentes(Font font) 
    {
        //TODO Auto-generated constructor stub
        this.font = font;
    }
    public Font getFont() 
    {
        return font;
    }
}
