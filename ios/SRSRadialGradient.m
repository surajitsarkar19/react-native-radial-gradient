#import "SRSRadialGradient.h"
#import <React/RCTConvert.h>
#import <UIKit/UIKit.h>
#import "SRSRadialGradientLayer.h"

@implementation SRSRadialGradient

+ (Class)layerClass
{
    return [SRSRadialGradientLayer class];
}

- (SRSRadialGradientLayer *)gradientLayer
{
    return (SRSRadialGradientLayer *)self.layer;
}

- (void)setColors:(NSArray *)colorStrings
{
    _colors = colorStrings;

    NSMutableArray *colors = [NSMutableArray arrayWithCapacity:colorStrings.count];
    for (NSString *colorString in colorStrings)
    {
        if ([colorString isKindOfClass:UIColor.class])
        {
            [colors addObject:(UIColor *)colorString];
        }
        else
        {
            [colors addObject:(id)[RCTConvert UIColor:colorString].CGColor];
            if([colorStrings count]==1){
                [colors addObject:(id)[[RCTConvert UIColor:colorString] colorWithAlphaComponent:0.5].CGColor];
            }
        }
    }
    self.gradientLayer.colors = colors;
}

- (void)setGradientCenter:(CGPoint)center
{
    _gradientCenter = center;
    self.gradientLayer.startCenter = center;
    self.gradientLayer.endCenter = center;
}

- (void)setRadius:(CGFloat)radius
{
    _radius = radius;
    self.gradientLayer.startRadius = 0;
    self.gradientLayer.endRadius = radius;
}

- (void)setStops:(NSArray *)stops
{
    _stops = stops;
    self.gradientLayer.locations = stops;
}

- (BOOL)respondsToSelector:(SEL)aSelector
{
    if (aSelector == @selector(displayLayer:))
    {
        return NO;
    }

    return [super respondsToSelector:aSelector];
}

@end

